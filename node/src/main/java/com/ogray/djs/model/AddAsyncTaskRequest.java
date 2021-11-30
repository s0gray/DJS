package com.ogray.djs.model;

import lombok.Getter;
import lombok.Setter;

public class AddAsyncTaskRequest {
    @Setter
    @Getter
    String className;

    @Setter @Getter
    String data;

    @Setter @Getter
    String callback;
}
