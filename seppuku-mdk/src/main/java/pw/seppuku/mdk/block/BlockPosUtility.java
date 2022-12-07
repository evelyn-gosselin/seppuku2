package pw.seppuku.mdk.block;

import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;

public final class BlockPosUtility {

  private BlockPosUtility() {
  }

  public static Stream<BlockPos> getBlockPosStreamWithinRadius(final BlockPos center,
      final float radius) {
    return getBlockPosStreamWithinRadiusMatchingPredicate(center, radius, blockPos -> true);
  }

  public static Stream<BlockPos> getBlockPosStreamWithinRadiusMatchingPredicate(
      final BlockPos center, final float radius, final Predicate<BlockPos> predicate) {
    final var from = center.offset(-radius, -radius, -radius);
    final var to = center.offset(radius, radius, radius);
    return BlockPos.betweenClosedStream(from, to).filter(predicate);
  }
}
