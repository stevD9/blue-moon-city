package me.stef.bluemooncity.op;

import me.stef.bluemooncity.properties.MySystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public abstract class MyAbstractOperations {

    @Autowired
    protected MySystem system;

    @Autowired
    protected ApplicationEventPublisher eventPublisher;
}
