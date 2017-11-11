/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.metron.elasticsearch.dao;

import org.apache.metron.indexing.dao.search.FieldType;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Responsible for retrieving column-level metadata about search indices.
 */
public interface ColumnMetadataDao {

  /**
   * Retrieves column metadata for one or more search indices.
   * @param indices The search indices to retrieve column metadata for.
   * @return The column metadata, one set for each search index.
   * @throws IOException
   */
  Map<String, Map<String, FieldType>> getColumnMetadata(List<String> indices) throws IOException;

  /**
   * Retrieves column metadata that is common across multiple search indices.
   * @param indices The search indices to retrieve column metadata for.
   * @return The column metadata that is common across all search indices.
   * @throws IOException
   */
  Map<String, FieldType> getCommonColumnMetadata(List<String> indices) throws IOException;

  /**
   * Finds the latest version of a set of base indices.  This can be used to find
   * the latest 'bro' index, for example.
   *
   * Assuming the following indices exist...
   *
   *    [
   *      'bro_index_2017.10.03.19'
   *      'bro_index_2017.10.03.20',
   *      'bro_index_2017.10.03.21',
   *      'snort_index_2017.10.03.19',
   *      'snort_index_2017.10.03.20',
   *      'snort_index_2017.10.03.21'
   *    ]
   *
   *  And the include indices are given as...
   *
   *    ['bro', 'snort']
   *
   * Then the latest indices are...
   *
   *    ['bro_index_2017.10.03.21', 'snort_index_2017.10.03.21']
   *
   * @param includeIndices The base names of the indices to include
   * @return The latest version of a set of indices.
   */
  String[] getLatestIndices(List<String> includeIndices);
}
