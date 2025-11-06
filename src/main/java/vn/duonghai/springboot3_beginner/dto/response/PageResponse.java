package vn.duonghai.springboot3_beginner.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class PageResponse<T> {

    int pageNo;
    int pageSize;
    int totalPages;
    T item;
}
