/*
 * Copyright 2019 Ivan Pekov (MrIvanPlays)

 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **/
package ml.extbukkit.main.secure.config;

import ml.extbukkit.api.config.Configuration;
import ml.extbukkit.api.util.Wrapper;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class YAMLConfiguration extends Wrapper<FileConfiguration> implements Configuration {

  private File file;

  public YAMLConfiguration(final FileConfiguration handle, File file) {
    super(handle);
    this.file = file;
  }

  @Override
  public String getString(final String path) {
    return handle.getString(path);
  }

  @Override
  public int getInt(final String path) {
    return handle.getInt(path);
  }

  @Override
  public List<String> getStringList(final String path) {
    return handle.getStringList(path);
  }

  @Override
  public List<Integer> getIntList(final String path) {
    return handle.getIntegerList(path);
  }

  @Override
  public double getDouble(final String path) {
    return handle.getDouble(path);
  }

  @Override
  public List<Double> getDoubleList(final String path) {
    return handle.getDoubleList(path);
  }

  @Override
  public float getFloat(final String path) {
    return Float.parseFloat(getDouble(path) + "f");
  }

  @Override
  public List<Float> getFloatList(final String path) {
    return handle.getFloatList(path);
  }

  @Override
  public Set<String> getKeys() {
    return handle.getKeys(false);
  }

  @Override
  public Configuration getSection(final String path) {
    return new YAMLConfiguration(handle, file);
  }

  @Override
  public boolean isSectionSet(final String path) {
    return handle.isSet(path);
  }

  @Override
  public void set(final String path, final Object value) {
    handle.set(path, value);
    try {
      handle.save(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Object get(final String path) {
    return handle.get(path);
  }
}
