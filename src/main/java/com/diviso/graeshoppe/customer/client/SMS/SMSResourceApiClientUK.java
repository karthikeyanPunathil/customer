package com.diviso.graeshoppe.customer.client.SMS;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "SMSResource", url= "${smsgateway.uk-url}")
public interface SMSResourceApiClientUK extends SMSResourceApiUK{

}
