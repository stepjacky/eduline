package org.jackysoft.edu.formbean;

public interface MediaFile {
    long getContentLength();
    String getContentType();
    String getFilename();
    String getRealpath();
}
