package pw.seppuku.client.feature.features

import pw.seppuku.client.components.client.KeyboardOnKey
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Keybind
import pw.seppuku.components.KeybindAction
import pw.seppuku.feature.*
import pw.seppuku.feature.repository.FeatureRepository

@PluginFeature
class KeybindListenerFeature(
    private val featureRepository: FeatureRepository,
) : AbstractFeature() {

    @Component
    private val humanIdentifier = HumanIdentifier("keybind_listener")

    @Component
    private val keyboardOnKey = KeyboardOnKey { _, key, _, actionCode, modifiers ->
        val action = KeybindAction.fromOrNull(actionCode) ?: return@KeyboardOnKey

        featureRepository.findAll()
            .mapComponent<Keybind>()
            .filter { it.key == key }
            .filter { it.modifiers == modifiers }
            .forEach { it.onAction(action) }
    }
}