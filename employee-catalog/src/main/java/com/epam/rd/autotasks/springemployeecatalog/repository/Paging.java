package com.epam.rd.autotasks.springemployeecatalog.repository;

import java.util.Arrays;

public class Paging {
    private enum SortParametres {
        LASTNAME("LASTNAME"),
        HIRED("HIREDATE"),
        POSITION("POSITION"),
        SALARY("SALARY");

        private final String label;

        SortParametres(String label) {
            this.label = label;
        }

        String getLabel() {
            return label;
        }
    }

    public static String addSort(String sort, String st) {
        if (sort.isBlank() || Arrays.stream(SortParametres.values()).allMatch(x -> x.getLabel().equals(sort.toUpperCase())))
            return st;
        else {
            switch (SortParametres.valueOf(sort.toUpperCase())) {
                case HIRED: {
                    st = st + " ORDER BY HIREDATE";
                    break;
                }
                case LASTNAME: {
                    st = st + " ORDER BY LASTNAME";
                    break;
                }
                case SALARY: {
                    st = st + " ORDER BY SALARY";
                    break;
                }
                case POSITION: {
                    st = st + " ORDER BY POSITION";
                    break;
                }
            }
        }
        return st;
    }

    public static String addPaging(Integer page, Integer size, String st) {
        if (size != null && size != 0) {
            st = st + " LIMIT " + size;
        }

        if (page != null && page != 0) {
            assert size != null;
            st = st + " OFFSET " + page * size;
        }
        return st;
    }
}
