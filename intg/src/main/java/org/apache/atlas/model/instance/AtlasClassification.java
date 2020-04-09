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
package org.apache.atlas.model.instance;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.atlas.model.PList;
import org.apache.atlas.model.SearchFilter.SortType;
import org.apache.atlas.model.TimeBoundary;
import org.apache.atlas.model.instance.AtlasEntity.Status;
import org.apache.atlas.type.AtlasClassificationType;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;


/**
 * An instance of a classification; it doesn't have an identity, this object exists only when associated with an entity.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AtlasClassification extends AtlasStruct implements Serializable {
    private static final long serialVersionUID = 1L;

    private String             entityGuid                        = null;
    private Status             entityStatus                      = Status.ACTIVE;
    private Boolean            propagate                         = null;
    private List<TimeBoundary> validityPeriods                   = null;
    private Boolean            removePropagationsOnEntityDelete  = null;
    /**
     * 增加父子类typeNames信息,使用List而非Set是为了避免json反序列化时默认用HashSet操作丢失顺序
     */
    private List<String> allSuperTypes = new LinkedList<>();
    private List<String> subTypes = new LinkedList<>();
    private List<String> allSubTypes = new LinkedList<>();
    private List<String> typeAndAllSubTypes = new LinkedList<>();
    private List<String> typeAndAllSuperTypes = new LinkedList<>();
    private String typeAndAllSubTypesQryStr = "";

    public AtlasClassification() {
        this(null, null);
    }

    public AtlasClassification(String typeName) {
        this(typeName, null);
    }

    public AtlasClassification(String typeName, Map<String, Object> attributes) {
        super(typeName, attributes);
    }

    public AtlasClassification(String typeName, String attrName, Object attrValue) {
        super(typeName, attrName, attrValue);
    }

    public AtlasClassification(Map map) {
        super(map);
    }

    public AtlasClassification(AtlasClassification other) {
        if (other != null) {
            setTypeName(other.getTypeName());
            setAttributes(other.getAttributes());
            setEntityGuid(other.getEntityGuid());
            setEntityStatus(other.getEntityStatus());
            setPropagate(other.isPropagate());
            setValidityPeriods(other.getValidityPeriods());
            setRemovePropagationsOnEntityDelete(other.getRemovePropagationsOnEntityDelete());
            getAllSuperTypes().addAll(other.getAllSuperTypes());
            getSubTypes().addAll(getSubTypes());
            getAllSubTypes().addAll(other.getAllSubTypes());
            getTypeAndAllSubTypes().addAll(other.getTypeAndAllSubTypes());
            getTypeAndAllSuperTypes().addAll(other.getTypeAndAllSuperTypes());
            setTypeAndAllSubTypesQryStr(other.getTypeAndAllSubTypesQryStr());
        }
    }

    public String getEntityGuid() {
        return entityGuid;
    }

    public void setEntityGuid(String entityGuid) {
        this.entityGuid = entityGuid;
    }

    public Boolean isPropagate() {
        return propagate;
    }

    public void setPropagate(Boolean propagate) {
        this.propagate = propagate;
    }

    public List<TimeBoundary> getValidityPeriods() {
        return validityPeriods;
    }

    public void setValidityPeriods(List<TimeBoundary> validityPeriods) {
        this.validityPeriods = validityPeriods;
    }

    public Status getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(Status entityStatus) {
        this.entityStatus = entityStatus;
    }

    public Boolean getRemovePropagationsOnEntityDelete() {
        return removePropagationsOnEntityDelete;
    }

    public void setRemovePropagationsOnEntityDelete(Boolean removePropagationsOnEntityDelete) {
        this.removePropagationsOnEntityDelete = removePropagationsOnEntityDelete;
    }

    public List<String> getAllSuperTypes() {
        return allSuperTypes;
    }

    public void setAllSuperTypes(List<String> allSuperTypes) {
        this.allSuperTypes = allSuperTypes;
    }

    public List<String> getSubTypes() {
        return subTypes;
    }

    public void setSubTypes(List<String> subTypes) {
        this.subTypes = subTypes;
    }

    public List<String> getAllSubTypes() {
        return allSubTypes;
    }

    public void setAllSubTypes(List<String> allSubTypes) {
        this.allSubTypes = allSubTypes;
    }

    public List<String> getTypeAndAllSubTypes() {
        return typeAndAllSubTypes;
    }

    public void setTypeAndAllSubTypes(List<String> typeAndAllSubTypes) {
        this.typeAndAllSubTypes = typeAndAllSubTypes;
    }

    public List<String> getTypeAndAllSuperTypes() {
        return typeAndAllSuperTypes;
    }

    public void setTypeAndAllSuperTypes(List<String> typeAndAllSuperTypes) {
        this.typeAndAllSuperTypes = typeAndAllSuperTypes;
    }

    public String getTypeAndAllSubTypesQryStr() {
        return typeAndAllSubTypesQryStr;
    }

    public void setTypeAndAllSubTypesQryStr(String typeAndAllSubTypesQryStr) {
        this.typeAndAllSubTypesQryStr = typeAndAllSubTypesQryStr;
    }

    public void setType(AtlasClassificationType type) {
        this.allSuperTypes.addAll(type.getAllSuperTypes());
        this.typeAndAllSuperTypes.addAll(type.getTypeAndAllSuperTypes());
        this.subTypes.addAll(type.getSubTypes());
        this.allSubTypes.addAll(type.getAllSubTypes());
        this.typeAndAllSubTypes.addAll(type.getTypeAndAllSubTypes());
        this.typeAndAllSubTypesQryStr = type.getTypeAndAllSubTypesQryStr();
    }

    @JsonIgnore
    public void addValityPeriod(TimeBoundary validityPeriod) {
        List<TimeBoundary> vpList = this.validityPeriods;

        if (vpList == null) {
            vpList = new ArrayList<>();

            this.validityPeriods = vpList;
        }

        vpList.add(validityPeriod);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        if (!super.equals(o)) { return false; }
        AtlasClassification that = (AtlasClassification) o;
        return Objects.equals(propagate, that.propagate) &&
               Objects.equals(removePropagationsOnEntityDelete, that.removePropagationsOnEntityDelete) &&
               Objects.equals(entityGuid, that.entityGuid) &&
               entityStatus == that.entityStatus &&
               Objects.equals(validityPeriods, that.validityPeriods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), entityGuid, entityStatus, propagate, removePropagationsOnEntityDelete);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AtlasClassification{");
        super.toString(sb);
        sb.append("entityGuid='").append(entityGuid).append('\'');
        sb.append(", entityStatus=").append(entityStatus);
        sb.append(", propagate=").append(propagate);
        sb.append(", removePropagationsOnEntityDelete=").append(removePropagationsOnEntityDelete);
        sb.append(", validityPeriods=").append(validityPeriods);
        sb.append(", validityPeriods=").append(validityPeriods);
        sb.append('}');
        return sb.toString();
    }

    /**
     * REST serialization friendly list.
     */
    @JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown=true)
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlSeeAlso(AtlasClassification.class)
    public static class AtlasClassifications extends PList<AtlasClassification> {
        private static final long serialVersionUID = 1L;

        public AtlasClassifications() {
            super();
        }

        public AtlasClassifications(List<AtlasClassification> list) {
            super(list);
        }

        public AtlasClassifications(List list, long startIndex, int pageSize, long totalCount,
                                    SortType sortType, String sortBy) {
            super(list, startIndex, pageSize, totalCount, sortType, sortBy);
        }
    }
}
