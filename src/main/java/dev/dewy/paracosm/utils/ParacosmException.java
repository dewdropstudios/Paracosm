package dev.dewy.paracosm.utils;

public class ParacosmException extends RuntimeException
{
    public ParacosmException()
    {
        super();
    }

    public ParacosmException(String msg)
    {
        super(msg);
    }

    public ParacosmException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ParacosmException(Throwable cause)
    {
        super(cause);
    }

    protected ParacosmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
