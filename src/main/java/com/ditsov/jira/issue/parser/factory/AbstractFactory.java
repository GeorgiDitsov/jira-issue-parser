package com.ditsov.jira.issue.parser.factory;

public interface AbstractFactory<E, T> {

    /**
     * Converts desired E entity, from JIRA REST API client, into data transfer
     * object T.
     *
     * @param e desired entity.
     *
     * @return data transfer object
     */
    public T convertToDTO(final E e);

}
