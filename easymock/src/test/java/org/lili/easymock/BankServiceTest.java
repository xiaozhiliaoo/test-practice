package org.lili.easymock;

import org.easymock.EasyMock;
import org.easymock.internal.MocksControl;
import org.junit.jupiter.api.Test;

/**
 * @author lili
 * @date 2020/11/25 2:22
 * @see
 * @since
 */
class BankServiceTest {

    @Test
    public void testWithdrawEncountersDataAccessException() {
        BackDao bankDao = EasyMock.createMock("bankDao", BackDao.class);
        bankDao.getWithdraw();

    }

}