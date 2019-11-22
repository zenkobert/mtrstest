package com.zezenk.mtrstest.converter;

import com.zezenk.mtrstest.dto.UserDTO;
import com.zezenk.mtrstest.model.TbUser;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserToUserDto implements Converter<TbUser, UserDTO> {

    @Synchronized
    @Nullable
    @Override
    public UserDTO convert(TbUser source) {
        if (source == null) return null;

        final UserDTO user = new UserDTO();
        user.setId(source.getId());
        user.setMobileNumber(source.getMobileNumber());
        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());
        user.setGender(source.getGender());
        user.setEmail(source.getEmail());

        if(source.getDateOfBirth() != null) {
            String year = source.getDateOfBirth().getYear() + "";
            String month = source.getDateOfBirth().getMonthValue() + "";
            String date = source.getDateOfBirth().getDayOfMonth() + "";

            user.setYear(year);
            user.setMonth(month);
            user.setDate(date);
        }

        return user;
    }
}
