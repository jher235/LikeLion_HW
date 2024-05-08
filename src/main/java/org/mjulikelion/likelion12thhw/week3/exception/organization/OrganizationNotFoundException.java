package org.mjulikelion.likelion12thhw.week3.exception.organization;

import org.mjulikelion.likelion12thhw.week3.exception.CustomException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;

public class OrganizationNotFoundException extends CustomException {
    public OrganizationNotFoundException() {
        super(ErrorCode.ORGANIZATION_NOT_FOUND);
    }

    public OrganizationNotFoundException(String detail) {
        super(ErrorCode.ORGANIZATION_NOT_FOUND, detail);
    }
}
