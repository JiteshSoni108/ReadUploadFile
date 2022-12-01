package com.fileread.filereaddemo.entity;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
public class FileInput {

    @NonNull
    private String filePath;

}
