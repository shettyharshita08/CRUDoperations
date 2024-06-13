package com.operations.catoperations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ResponseDTO implements Serializable {
    private MetaDTO meta;
    private Object data;

    public ResponseDTO() {
    }

    public ResponseDTO(MetaDTO meta, Object data) {
        this.meta = meta;
        this.data = data;
    }

    @JsonProperty("meta")
    public MetaDTO getMeta() {
        return meta;
    }

    public void setMeta(MetaDTO meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
