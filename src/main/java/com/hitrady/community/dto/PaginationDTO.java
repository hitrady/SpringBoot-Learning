package com.hitrady.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions = new ArrayList<>();
    private List<Integer> pages = new ArrayList<>();
    private Integer page;
    private boolean firstPage;
    private boolean prePage;
    private boolean lastPage;
    private boolean nextPage;
    private Integer totalPage;

    public void setCurrentPage(Integer totalSize, Integer page, Integer size) {
        if(totalSize%size==0){
            totalPage = totalSize/size;
        }else {
            totalPage = totalSize/size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        this.page = page;
        if (page == 1) {
            prePage = false;
        } else {
            prePage = true;
        }

        if (page == totalPage) {
            nextPage = false;
        }else {
            nextPage = true;
        }

        if(page-4 >= 1){
            firstPage =true;
        }else {
            firstPage = false;
        }

        if(page+4 <= totalPage){
            lastPage = true;
        }else {
            lastPage = false;
        }

        for(int i = 1 ;i<=3;i++){
            if(page-i>0){
                pages.add(page-i);
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }
        pages.add(page);
        Collections.sort(pages);
    }
}
