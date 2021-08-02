package com.cleviro.ErpManagerApp.util;

import com.cleviro.ErpManagerApp.exception.ERPException;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;

public class ExceptionUtil {
    /**
     * Throw new RuntimeException
     * @param entityType,ExceptionType,args
     *
     */
    public static RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args)
    {
        return ERPException.throwException(entityType, exceptionType,args);
    }
}
