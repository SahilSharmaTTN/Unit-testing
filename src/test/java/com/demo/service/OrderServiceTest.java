package com.demo.service;

import com.demo.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    EmailService emailService;

    @Mock
    Order order;

    @InjectMocks
    OrderService orderService;

    @Test
    public void placeOrderBooleanTest(){

        when(order.getPrice()).thenReturn(200.0);

        when(order.setPriceWithTax(220)).thenReturn(order);

        when(emailService.sendEmail(order,"test")).thenReturn(true);

        orderService.placeOrder(order,"test");

        verify(order).setCustomerNotified(true);

    }

    @Test
    public void placeOrderVoidTest(){

        when(order.getPrice()).thenReturn(200.0);

        when(order.setPriceWithTax(220)).thenReturn(order);

        orderService.placeOrder(order);

        verify(emailService,atLeast(1)).sendEmail(order);

        verify(order,atLeastOnce()).setCustomerNotified(true);

    }
}

