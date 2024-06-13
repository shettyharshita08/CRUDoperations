package com.operations.catoperations.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cat_information")
public class CatDataEntity {
    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer image_id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "type")
    private String type;

    @Column(name = "filepath")
    private String filepath;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserInfoEntity user_id;

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public UserInfoEntity getUser_id() {
        return user_id;
    }

    public void setUser_id(UserInfoEntity user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "CatDataEntity{" +
                "image_id=" + image_id +
                ", filename='" + filename + '\'' +
                ", type='" + type + '\'' +
                ", filepath='" + filepath + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
