package com.business.electr.clothes.net;

import com.business.electr.clothes.net.exception.ApiException;

import io.reactivex.functions.Function;

public class ResponseConvert<E> implements Function<BaseApiResponse<E>, BaseApiResponse<E>> {
    @Override
    public BaseApiResponse<E> apply(BaseApiResponse<E> response) {
        if (response.getStatus() != 0) {
            throw new ApiException(response.getStatus(), response.getMsg());
        }
        return response;
    }
}
