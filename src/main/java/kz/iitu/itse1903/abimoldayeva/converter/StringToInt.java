package kz.iitu.itse1903.abimoldayeva.converter;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import org.springframework.core.convert.converter.Converter;

public class StringToInt implements Converter<String, Client> {
    @Override
    public Client convert(String source) {
        String []data = source.split(",");
        Client client = new Client();
        client.setId(Long.parseLong(data[0]));
        return client;
    }
}
