package com.test.demo.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.test.demo.dto.UserDto;
import com.test.demo.form.user.CreateUserForm;
import com.test.demo.form.user.UpdateUserForm;
import com.test.demo.model.User;

import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(source = "createUserName", target = "name")
    @Mapping(source = "createUserPhone", target = "phone")
    @Mapping(source = "createUserEmail", target = "email")
    @Mapping(source = "createUserAddress", target = "address")
    User fromCreateFormToUser(CreateUserForm createUserForm);

    @Mapping(source = "updateUserName", target = "name")
    @Mapping(source = "updateUserPhone", target = "phone")
    @Mapping(source = "updateUserEmail", target = "email")
    @Mapping(source = "updateUserAddress", target = "address")
    void fromUpdateFormToEntity(UpdateUserForm updateUserForm, @MappingTarget User user);

    @Mapping(source = "id", target = "dtoId")
    @Mapping(source = "name", target = "dtoName")
    @Mapping(source = "phone", target = "dtoPhone")
    @Mapping(source = "email", target = "dtoEmail")
    @Mapping(source = "address", target = "dtoAddress")
    @Mapping(source = "createdDate", target = "dtoCreatedDate")
    UserDto fromUserToDto(User user);

    @IterableMapping(elementTargetType = UserDto.class)
    List<UserDto> fromEntityListToDtoList(List<User> content);
}
