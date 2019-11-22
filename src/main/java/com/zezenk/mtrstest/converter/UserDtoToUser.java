package com.zezenk.mtrstest.converter;

import com.zezenk.mtrstest.dto.UserDTO;
import com.zezenk.mtrstest.model.TbUser;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserDtoToUser implements Converter<UserDTO, TbUser> {

    @Synchronized
    @Nullable
    @Override
    public TbUser convert(UserDTO source) {
        if (source == null) return null;

        final TbUser user = new TbUser();
        user.setId(source.getId());
        user.setMobileNumber(source.getMobileNumber());
        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());
        user.setGender(source.getGender());
        user.setEmail(source.getEmail());

        if (isValidDate(source)) {
            String fullDate = source.getYear() + "-"
                    + String.format("%02d", Integer.parseInt(source.getMonth())) + "-"
                    + String.format("%02d", Integer.parseInt(source.getDate()));

            user.setDateOfBirth(LocalDate.parse(fullDate));
        }

        return user;
    }

    private boolean isValidDate(UserDTO source) {
        return (
                source.getDate() != null && !source.getDate().equals("none") &&
                source.getMonth() != null && !source.getMonth().equals("none") &&
                source.getYear() != null && !source.getYear().equals("none")
        );
    }
}
