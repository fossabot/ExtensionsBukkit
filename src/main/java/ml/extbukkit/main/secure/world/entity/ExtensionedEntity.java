package ml.extbukkit.main.secure.world.entity;

import com.google.gson.JsonObject;
import ml.extbukkit.api.server.Server;
import ml.extbukkit.api.types.EntityType;
import ml.extbukkit.api.world.Position;
import ml.extbukkit.api.world.StraightDirection;
import ml.extbukkit.api.world.entity.Entity;
import ml.extbukkit.main.secure.command.ExtensionedCommandExecutor;
import ml.extbukkit.main.secure.nms.NBTUtils;
import ml.extbukkit.main.secure.world.DirectionHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExtensionedEntity extends ExtensionedCommandExecutor implements Entity {

  private org.bukkit.entity.Entity base;

  public ExtensionedEntity(org.bukkit.entity.Entity handle) {
    super(handle);
    this.base = handle;
  }

  @Override
  public boolean addPassenger(ml.extbukkit.api.world.entity.Entity passenger) {
    return base.addPassenger(((ExtensionedEntity) passenger).base);
  }

  @Override
  public boolean ejectPassengers() {
    return base.eject();
  }

  @Override
  public void getBoundingBox() {
    // TODO
  }

  @Override
  public int getId() {
    return base.getEntityId();
  }

  @Override
  public StraightDirection getDirection() {
    return DirectionHelper.bukkitToStraight(base.getFacing());
  }

  @Override
  public double getHeight() {
    return base.getHeight();
  }

  @Override
  public void getDamageEvent() {
    // TODO
  }

  @Override
  public List<ml.extbukkit.api.world.entity.Entity> getPassengers() {
    List<ml.extbukkit.api.world.entity.Entity> ents = new ArrayList<>();
    for(org.bukkit.entity.Entity ent : base.getPassengers()) {
      ents.add(new ExtensionedEntity(ent));
    }
    return ents;
  }

  @Override
  public int getTicksExist() {
    return base.getTicksLived();
  }

  @Override
  public EntityType getType() {
    return EntityHelper.bukkitToEB(base.getType());
  }

  @Override
  public UUID getUUID() {
    return base.getUniqueId();
  }

  @Override
  public ml.extbukkit.api.world.entity.Entity getPassengerOf() {
    return new ExtensionedEntity(base.getVehicle());
  }

  @Override
  public double getWidth() {
    return base.getWidth();
  }

  @Override
  public boolean isMarkedForRemoval() {
    return base.isDead();
  }

  @Override
  public boolean hasPassengers() {
    return !base.getPassengers().isEmpty();
  }

  @Override
  public boolean isPassenger() {
    return base.isInsideVehicle();
  }

  @Override
  public boolean isExist() {
    return base.isValid();
  }

  @Override
  public boolean ejectAsPassenger() {
    return base.leaveVehicle();
  }

  @Override
  public void remove() {
    base.remove();
  }

  @Override
  public boolean removePassenger(ml.extbukkit.api.world.entity.Entity passenger) {
    return base.removePassenger(((ExtensionedEntity) passenger).base);
  }

  @Override
  public void setDamageEvent(Object event) {
    // TODO
  }

  @Override
  public boolean setPassenger(ml.extbukkit.api.world.entity.Entity passenger) {
    return ejectPassengers() && addPassenger(passenger);
  }

  @Override
  public void setTicksExist(int exist) {
    base.setTicksLived(exist);
  }

  @Override
  public JsonObject getNBT() {
    return NBTUtils.nbtToJson(NBTUtils.getEntityNbt(base));
  }

  @Override
  public void updateNBT(JsonObject nbt) {
    NBTUtils.setEntityNbt(base, NBTUtils.jsonToNbt(nbt));
  }

  @Override
  public Position getPosition() {
    Location baseLoc = base.getLocation();
    return Server.getInstance().getWorldManager().getWorld(base.getWorld().getName())
      .positionRotated(baseLoc.getX(), baseLoc.getY(), baseLoc.getZ(), baseLoc.getYaw(), baseLoc.getPitch());
  }

  @Override
  public boolean setPosition(Position position) {
    return base.teleport(
      new Location(Bukkit.getWorld(position.getWorld()), position.getX(), position.getY(), position.getZ(), position.getYaw(), position.getPitch()));
  }
}