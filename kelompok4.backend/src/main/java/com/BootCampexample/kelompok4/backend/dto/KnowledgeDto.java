package com.BootCampexample.kelompok4.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class KnowledgeDto {

    @Data
    @NoArgsConstructor
    public static class Create{
        @Size(min = 5)
        @NotEmpty
        @NotNull
        String nama;

        @Min(1)
        @NotNull
        Integer rating;

        @Size(min = 5)
        @NotEmpty
        @NotNull
        String status;

        @Min(1)
        @NotNull
        Integer app_users_id;

    }

    @Data
    @NoArgsConstructor
    public static class Update {

        @Min(1)
        @NotNull
        Integer id;

        @Size(min = 5)
        @NotEmpty
        @NotNull
        String nama;

        @Min(1)
        @NotNull
        Integer rating;

        @Size(min = 5)
        @NotEmpty
        @NotNull
        String status;

        @Min(1)
        @NotNull
        Integer app_users_id;


    }
}
