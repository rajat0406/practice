package com.extramarks.journeybuilder.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class StageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Cannot be empty!")
    private String contentName;
    @NotNull(message = "Cannot be empty!")
    private String actionName;

    public StageModel(String stage, String action) {
        this.contentName = stage;
        this.actionName = action;
    }
    
    public StageModel() {}
    
    public Long getId() {
		return id;
	}

	public StageModel setId(Long id) {
		this.id = id;
		return this;
	}

	public String getContentName() {
		return contentName;
	}

	public StageModel setContentName(String contentName) {
		this.contentName = contentName;
		return this;
	}

	public String getActionName() {
		return actionName;
	}

	public StageModel setActionName(String actionName) {
		this.actionName = actionName;
		return this;
	}

	@Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || this.getClass() != that.getClass()) return false;
        StageModel stage = (StageModel) that;
        return Objects.equals(contentName, stage.contentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentName);
    }

    @Override
    public String toString() {
        return "[" + this.contentName + "]";
    }
}
