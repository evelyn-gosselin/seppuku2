package pw.seppuku.client.feature.persistent;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import pw.seppuku.events.SeppukuEventsPlugin;
import pw.seppuku.feature.persistent.PersistentFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.Plugin;
import pw.seppuku.plugin.exception.exceptions.DuplicateUniqueIdentifierPluginException;
import pw.seppuku.plugin.repository.PluginRepository;
import pw.seppuku.resolver.Inject;
import pw.seppuku.resolver.Resolver;

// TODO: Clean this mess up... At some point... Maybe...
public final class PluginLoaderFeature extends PersistentFeature {

  private static final UUID PLUGIN_LOADER_UNIQUE_IDENTIFIER = UUID.fromString(
      "77e24308-d103-46a9-bb13-e97b7b08062f");
  private static final String PLUGIN_LOADER_HUMAN_IDENTIFIER = "plugin_loader";
  private static final Version PLUGIN_LOADER_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> PLUGIN_LOADER_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  private static final File PLUGIN_LOADER_DISCOVERY_DIRECTORY = new File("seppuku/plugins/");

  private final Resolver resolver;
  private final PluginRepository pluginRepository;

  @Inject
  public PluginLoaderFeature(final Resolver resolver, final PluginRepository pluginRepository) {
    super(PLUGIN_LOADER_UNIQUE_IDENTIFIER, PLUGIN_LOADER_HUMAN_IDENTIFIER, PLUGIN_LOADER_VERSION,
        PLUGIN_LOADER_AUTHORS);
    this.resolver = resolver;
    this.pluginRepository = pluginRepository;
  }

  @Override
  public void load() {
    if (!PLUGIN_LOADER_DISCOVERY_DIRECTORY.exists()
        && !PLUGIN_LOADER_DISCOVERY_DIRECTORY.mkdirs()) {
      throw new RuntimeException("Could not create directory " + PLUGIN_LOADER_DISCOVERY_DIRECTORY);
    }

    addBundledEventsPlugin();
    addExternalPlugins();

    for (final var plugin : pluginRepository) {
      try {
        plugin.load();
      } catch (final Exception exception) {
        exception.printStackTrace();
      }
    }
  }

  @Override
  public void unload() {
    for (final var plugin : pluginRepository) {
      try {
        plugin.unload();
      } catch (final Exception exception) {
        exception.printStackTrace();
      }
    }
  }

  private void addBundledEventsPlugin() {
    final var seppukuEventsPlugin = new SeppukuEventsPlugin();

    try {
      pluginRepository.add(seppukuEventsPlugin);
    } catch (final DuplicateUniqueIdentifierPluginException exception) {
      exception.printStackTrace();
      throw new RuntimeException(exception);
    }
  }

  private void addExternalPlugins() {
    final var externalPluginFiles = discoverExternalPluginFiles();
    externalPluginFiles.forEach(this::addExternalPlugin);
  }

  private List<File> discoverExternalPluginFiles() {
    final var pluginFiles = PLUGIN_LOADER_DISCOVERY_DIRECTORY.listFiles();
    if (pluginFiles == null) {
      throw new RuntimeException(
          "Could not list files in directory " + PLUGIN_LOADER_DISCOVERY_DIRECTORY);
    }

    return Arrays.stream(pluginFiles).filter(File::isFile).toList();
  }

  private void addExternalPlugin(final File pluginFile) {
    final var pluginUrl = getPluginUrl(pluginFile);
    final var pluginClassLoader = createPluginClassLoader(pluginUrl);

    final var pluginZipFile = readPluginZip(pluginFile);
    final var pluginZipClassEntries = getPluginZipClassEntries(pluginZipFile);

    final var pluginClasses = loadPluginClasses(pluginClassLoader, pluginZipClassEntries);
    final var pluginInstances = createPluginInstances(pluginClasses);

    pluginInstances.forEach(plugin -> {
      try {
        pluginRepository.add(plugin);
      } catch (final DuplicateUniqueIdentifierPluginException exception) {
        exception.printStackTrace();
        throw new RuntimeException(exception);
      }
    });
  }

  private URL getPluginUrl(final File pluginFile) {
    try {
      return pluginFile.toURI().toURL();
    } catch (final MalformedURLException exception) {
      exception.printStackTrace();
      throw new RuntimeException(exception);
    }
  }

  private ClassLoader createPluginClassLoader(final URL pluginUrl) {
    return new URLClassLoader(new URL[]{pluginUrl}, getClass().getClassLoader());
  }

  private ZipFile readPluginZip(final File pluginFile) {
    try {
      return new ZipFile(pluginFile);
    } catch (final IOException exception) {
      exception.printStackTrace();
      throw new RuntimeException(exception);
    }
  }

  private List<? extends ZipEntry> getPluginZipClassEntries(final ZipFile pluginZipFile) {
    return pluginZipFile.stream().filter(zipEntry -> zipEntry.getName().endsWith(".class"))
        .toList();
  }

  private List<Class<?>> loadPluginClasses(final ClassLoader pluginClassLoader,
      final List<? extends ZipEntry> pluginZipClassEntries) {
    final var classes = new ArrayList<Class<?>>();

    pluginZipClassEntries.forEach(zipEntry -> {
      try {
        final var pluginClass = pluginClassLoader.loadClass(
            zipEntry.getName().substring(0, zipEntry.getName().length() - 6).replace("/", "."));
        classes.add(pluginClass);
      } catch (final ClassNotFoundException exception) {
        exception.printStackTrace();
        throw new RuntimeException(exception);
      }
    });

    return classes;
  }

  private List<Plugin> createPluginInstances(final List<Class<?>> pluginClasses) {
    return pluginClasses.stream().filter(Plugin.class::isAssignableFrom).map(pluginClass -> {
      try {
        return resolver.create(pluginClass);
      } catch (final InstantiationException | IllegalAccessException | InvocationTargetException exception) {
        exception.printStackTrace();
        throw new RuntimeException(exception);
      }
    }).map(Plugin.class::cast).toList();
  }
}
