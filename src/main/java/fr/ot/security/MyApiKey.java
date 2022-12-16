package fr.ot.security;


import fr.ot.entities.ApiKeysEntity;
import fr.ot.repository.ApiKeysRepository;

import java.util.Base64;

public class MyApiKey {

    public final static String APIKEY_PREFIX = "ApiKey";

    private MyApiKey() {
    }

    public static String generateApiKey() {
        double randomDouble = Math.random() * 64;
        byte[] encodedBytes = Base64.getEncoder().encode(String.valueOf(randomDouble).getBytes());
        return new String(encodedBytes);
    }

    public static boolean exist(String apiKey) {
        ApiKeysRepository apiKeysRepository = new ApiKeysRepository();
        return apiKeysRepository.find("api_key", apiKey).firstResult() != null;
    }

    public static ApiKeysEntity getByApiKey(String apiKey){
        ApiKeysRepository apiKeysRepository = new ApiKeysRepository();
        return apiKeysRepository.find("api_key", apiKey).firstResult();
    }

    public static void addUse(ApiKeysEntity apiKeysEntity){
        ApiKeysRepository apiKeysRepository = new ApiKeysRepository();
        apiKeysEntity.setUses(apiKeysEntity.getUses() + 1);
        apiKeysRepository.update("uses", apiKeysEntity);
    }

}
