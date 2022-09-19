package com.example.knowledge.advice;

import com.example.knowledge.label.LabelKey;
import com.example.knowledge.label.Labels;

public class DecryptErrorException extends RuntimeException {

    /** The Constant serialVersionUID */
    private static final long serialVersionUID = 4349992233530992213L;
    
    public DecryptErrorException() {
        super(Labels.getLabels(LabelKey.ERROR_CANNOT_DECRYPT_DATA));
    }

    public DecryptErrorException(String message) {
        super(message);
    }

    public DecryptErrorException(String message, Throwable t) {
        super(message, t);
    }

}
