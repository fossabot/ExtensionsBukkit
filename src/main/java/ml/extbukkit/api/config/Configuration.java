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
package ml.extbukkit.api.config;

import java.util.List;
import java.util.Set;

/**
 * Represents configuration
 */
public interface Configuration {

  /**
   * Gets string from the configuration
   *
   * @param path path
   * @return string
   */
  String getString(String path);

  /**
   * Gets int from the configuration
   *
   * @param path path
   * @return int
   */
  int getInt(String path);

  /**
   * Gets list of string from the configuration
   *
   * @param path path
   * @return list of string
   */
  List<String> getStringList(String path);

  /**
   * Gets list of integers from the configuration
   *
   * @param path path
   * @return list of integers
   */
  List<Integer> getIntList(String path);

  /**
   * Gets double from the configuration
   *
   * @param path path
   * @return double
   */
  double getDouble(String path);

  /**
   * Gets list of doubles from the configuration
   *
   * @param path path
   * @return list of doubles
   */
  List<Double> getDoubleList(String path);

  /**
   * Gets float from the configuration
   *
   * @param path path
   * @return float
   */
  float getFloat(String path);

  /**
   * Gets list of floats from the configuration
   *
   * @param path path
   * @return list of floats
   */
  List<Float> getFloatList(String path);

  /**
   * Gets the keys of the configuration.
   *
   * @return keys
   */
  Set<String> getKeys();

  /**
   * Gets a configuration section
   *
   * @param path path
   * @return configuration section
   */
  Configuration getSection(String path);

  /**
   * Checks if a configuration section is set
   *
   * @param path path
   * @return true if configuration section is set
   */
  boolean isSectionSet(String path);

  /**
   * Sets the path to the value.
   * No need of saving. We do it for you :)
   *
   * @param path path
   * @param value value
   */
  void set(String path, Object value);

  /**
   * Gets thing from the configuration.
   * <b>Not recommended</b>
   *
   * @param path path
   * @return object
   */
  Object get(String path);
}
