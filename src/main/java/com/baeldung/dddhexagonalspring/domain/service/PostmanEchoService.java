package com.baeldung.dddhexagonalspring.domain.service;

public interface PostmanEchoService {

    Object getEcho(String request);

    Object postEcho(String request, Object body);
}
