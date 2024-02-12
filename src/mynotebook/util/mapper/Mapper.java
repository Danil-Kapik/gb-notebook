package mynotebook.util.mapper;

import mynotebook.model.User;

public abstract class Mapper {

    public abstract String toInput(User user);

    public abstract User toOutput(String s);
}
