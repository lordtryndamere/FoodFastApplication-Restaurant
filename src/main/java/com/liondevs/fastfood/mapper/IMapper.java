package com.liondevs.fastfood.mapper;
//los mappers son funciones que a partir de un objeto se mapea a otro
public interface IMapper<I,O> {
    public O map(I in);
}
