package net.kingdomsofarden.townships.regions.bounds;

import com.google.gson.JsonObject;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.regions.CuboidRegion;
import net.kingdomsofarden.townships.api.regions.bounds.BoundingArea;
import net.kingdomsofarden.townships.api.regions.bounds.CuboidBoundingBox;
import net.kingdomsofarden.townships.regions.bounds.wrappers.WrappedBoundingArea;

public class AxisAlignedBoundingBox extends WrappedBoundingArea implements CuboidBoundingBox {

    private final Vector min;
    private final Vector max;

    public AxisAlignedBoundingBox(CuboidRegion cube) {
        super(cube);
        this.min = cube.getMinimumPoint();
        this.max = cube.getMaximumPoint();
    }

    @Override public int getMinX() {
        return min.getBlockX();
    }

    @Override public int getMaxX() {
        return max.getBlockX();
    }

    @Override public int getMinY() {
        return min.getBlockY();
    }

    @Override public int getMaxY() {
        return max.getBlockY();
    }

    @Override public int getMinZ() {
        return min.getBlockZ();
    }

    @Override public int getMaxZ() {
        return max.getBlockZ();
    }

    @Override public CuboidRegion getBackingBounds() {
        return (CuboidRegion) bounds;
    }

    @Override public <T extends BoundingArea> T grow(Class<T> clazz, int size) {
        Vector newMin = new Vector(min.getX() - size, min.getY() - size, min.getZ() - size);
        Vector newMax = new Vector(max.getX() + size, max.getY() + size, max.getZ() + size);
        return (T) new AxisAlignedBoundingBox(new CuboidRegion(newMin, newMax));
    }

    @Override public void initialize(JsonObject json) {

    }

    @Override public JsonObject save() {
        return null;
    }

    @Override public void computeVertices() {
        this.vertices.add(new Vector(getMinX(), getMinY(), getMinZ()));
        this.vertices.add(new Vector(getMinX(), getMaxY(), getMinZ()));
        this.vertices.add(new Vector(getMinX(), getMinY(), getMaxZ()));
        this.vertices.add(new Vector(getMinX(), getMaxY(), getMaxZ()));
        this.vertices.add(new Vector(getMaxX(), getMinY(), getMinZ()));
        this.vertices.add(new Vector(getMaxX(), getMaxY(), getMinZ()));
        this.vertices.add(new Vector(getMaxX(), getMinY(), getMaxZ()));
        this.vertices.add(new Vector(getMaxX(), getMaxY(), getMaxZ()));
    }
}
