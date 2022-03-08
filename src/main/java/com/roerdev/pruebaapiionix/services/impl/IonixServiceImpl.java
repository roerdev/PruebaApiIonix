package com.roerdev.pruebaapiionix.services.impl;

import com.roerdev.pruebaapiionix.consumers.IonixAPI;
import com.roerdev.pruebaapiionix.dto.ConsultaRutDto;
import com.roerdev.pruebaapiionix.exceptions.NotFoundException;
import com.roerdev.pruebaapiionix.services.IonixService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Slf4j
@Service
@RequiredArgsConstructor
public class IonixServiceImpl implements IonixService {

    private final IonixAPI ionixApiConsumer;

    @Override
    public Integer consultaRut(String param) throws IOException {
        var encryptedRut = "";
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            DESKeySpec keySpec = new DESKeySpec("ionix123456".getBytes("UTF8"));
            SecretKey ks = keyFactory.generateSecret(keySpec);

            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, ks);

            byte [] cleartext = param.getBytes("UTF8");
            encryptedRut = Base64.getEncoder().encodeToString(cipher.doFinal(cleartext));

        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        var callResponse = ionixApiConsumer.consultaRut(encryptedRut);
        var response = callResponse.execute();
        log.info("{}", response);

        if (response.code() == 200) {
            var body = response.body();
            if (body != null) {
                log.info("{}",body);
                return body.getResult().getItems().size();
            }
        } else{
            throw new NotFoundException("Error el respuesta del servicio externo.");
        }
        return null;
    }
}
