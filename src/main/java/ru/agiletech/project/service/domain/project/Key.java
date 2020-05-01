package ru.agiletech.project.service.domain.project;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import ru.agiletech.project.service.domain.supertype.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Key implements ValueObject {

    private String value;

    static Key createFromName(String name){
        String[] words = name.split(StringUtils.SPACE);
        String value = generateKeyValue(words);

        return new Key(value);
    }

    public static Key createFromKeyValue(String value){
        return new Key(value);
    }

    private static String generateKeyValue(String[] words){
        StringBuilder value = new StringBuilder();

        for(String word: words){
            value.append(word.substring(0, 1)
                    .toUpperCase());
        }

        return value.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object == null
                || getClass() != object.getClass())
            return false;

        Key key = (Key) object;
        return Objects.equals(value,
                key.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
