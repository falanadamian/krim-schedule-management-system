package com.falanadamian.krim.schedule.domain.dto;

import javax.persistence.Lob;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class DocumentDTO implements Serializable {

    private Long id;

    @Lob
    private byte[] file;
    private String fileContentType;

    private String fileName;

    private String title;

    private String description;

    private Long errandId;

    private Long createdById;

    private Long stateDetailsId;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getErrandId() {
        return errandId;
    }

    public void setErrandId(Long errandId) {
        this.errandId = errandId;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long myUserId) {
        this.createdById = myUserId;
    }

    public Long getStateDetailsId() {
        return stateDetailsId;
    }

    public void setStateDetailsId(Long stateTrackerId) {
        this.stateDetailsId = stateTrackerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long myUserId) {
        this.userId = myUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentDTO that = (DocumentDTO) o;
        return Objects.equals(id, that.id) &&
            Arrays.equals(file, that.file) &&
            Objects.equals(fileContentType, that.fileContentType) &&
            Objects.equals(fileName, that.fileName) &&
            Objects.equals(title, that.title) &&
            Objects.equals(description, that.description) &&
            Objects.equals(errandId, that.errandId) &&
            Objects.equals(createdById, that.createdById) &&
            Objects.equals(stateDetailsId, that.stateDetailsId) &&
            Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, fileContentType, fileName, title, description, errandId, createdById, stateDetailsId, userId);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
            "id=" + id +
            ", file=" + Arrays.toString(file) +
            ", fileContentType='" + fileContentType + '\'' +
            ", fileName='" + fileName + '\'' +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", errandId=" + errandId +
            ", createdById=" + createdById +
            ", stateDetailsId=" + stateDetailsId +
            ", userId=" + userId +
            '}';
    }
}
