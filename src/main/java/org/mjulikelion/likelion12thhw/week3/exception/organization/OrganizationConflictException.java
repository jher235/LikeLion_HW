package org.mjulikelion.likelion12thhw.week3.exception.organization;

import org.mjulikelion.likelion12thhw.week3.exception.CustomException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;

public class OrganizationConflictException extends CustomException {
    public OrganizationConflictException() {
        super(ErrorCode.ORGANIZATION_CONFLICT);
    }

    public OrganizationConflictException(String detail) {
        super(ErrorCode.ORGANIZATION_CONFLICT, detail);
    }
}
