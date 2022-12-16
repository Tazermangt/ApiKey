package fr.ot.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;



@Getter
@Setter
@Entity
@Table(name = "ApiKeys", schema = "dbo", catalog = "ApiKey")
public class ApiKeysEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "api_key_id")
    private int apiKeyId;
    @Basic
    @Column(name = "api_key")
    private String apiKey;
    @Basic
    @Column(name = "uses")
    private Integer uses;

    public ApiKeysEntity(String apiKey) {
        this.apiKeyId = 0;
        this.apiKey = apiKey;
        this.uses = 0;
    }

    public ApiKeysEntity() {
        this.apiKeyId = 0;
        this.apiKey = "";
        this.uses = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiKeysEntity that = (ApiKeysEntity) o;
        return apiKeyId == that.apiKeyId && Objects.equals(apiKey, that.apiKey) && Objects.equals(uses, that.uses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiKeyId, apiKey, uses);
    }
}
