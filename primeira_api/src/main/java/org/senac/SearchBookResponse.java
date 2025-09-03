package org.senac;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.util.ArrayList;
import java.util.List;

public class SearchBookResponse {

    public List<PanacheEntityBase> Books = new ArrayList<PanacheEntityBase>();
    public long TotalBooks;
    public int TotalPages;
    public boolean HasMore;
    public String NextPage;


}
