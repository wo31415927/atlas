/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.atlas.model.instance;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.atlas.type.AtlasClassificationType;
import org.springframework.beans.BeanUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/** 在AtlasClassificationWithType基础上增加了AtlasClassificationType信息 */
@JsonAutoDetect(
    getterVisibility = PUBLIC_ONLY,
    setterVisibility = PUBLIC_ONLY,
    fieldVisibility = NONE)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AtlasClassificationWithType extends AtlasClassification {
  private Set<String> allSuperTypes = new LinkedHashSet<>();
  private Set<String> subTypes = new LinkedHashSet<>();
  private Set<String> allSubTypes = new LinkedHashSet<>();
  private Set<String> typeAndAllSubTypes = new LinkedHashSet<>();
  private Set<String> typeAndAllSuperTypes = new LinkedHashSet<>();
  private String typeAndAllSubTypesQryStr = "";

  public AtlasClassificationWithType() {}

  public AtlasClassificationWithType(AtlasClassification classification) {
    BeanUtils.copyProperties(classification, this);
  }

  public Set<String> getAllSuperTypes() {
    return allSuperTypes;
  }

  public void setAllSuperTypes(Set<String> allSuperTypes) {
    this.allSuperTypes = allSuperTypes;
  }

  public Set<String> getSubTypes() {
    return subTypes;
  }

  public void setSubTypes(Set<String> subTypes) {
    this.subTypes = subTypes;
  }

  public Set<String> getAllSubTypes() {
    return allSubTypes;
  }

  public void setAllSubTypes(Set<String> allSubTypes) {
    this.allSubTypes = allSubTypes;
  }

  public Set<String> getTypeAndAllSubTypes() {
    return typeAndAllSubTypes;
  }

  public void setTypeAndAllSubTypes(Set<String> typeAndAllSubTypes) {
    this.typeAndAllSubTypes = typeAndAllSubTypes;
  }

  public Set<String> getTypeAndAllSuperTypes() {
    return typeAndAllSuperTypes;
  }

  public void setTypeAndAllSuperTypes(Set<String> typeAndAllSuperTypes) {
    this.typeAndAllSuperTypes = typeAndAllSuperTypes;
  }

  public String getTypeAndAllSubTypesQryStr() {
    return typeAndAllSubTypesQryStr;
  }

  public void setTypeAndAllSubTypesQryStr(String typeAndAllSubTypesQryStr) {
    this.typeAndAllSubTypesQryStr = typeAndAllSubTypesQryStr;
  }

  public AtlasClassificationWithType setType(AtlasClassificationType type) {
    this.allSuperTypes.addAll(type.getAllSuperTypes());
    this.typeAndAllSuperTypes.addAll(type.getTypeAndAllSuperTypes());
    this.subTypes.addAll(type.getSubTypes());
    this.allSubTypes.addAll(type.getAllSubTypes());
    this.typeAndAllSubTypes.addAll(type.getTypeAndAllSubTypes());
    this.typeAndAllSubTypesQryStr = type.getTypeAndAllSubTypesQryStr();
    return this;
  }
}
