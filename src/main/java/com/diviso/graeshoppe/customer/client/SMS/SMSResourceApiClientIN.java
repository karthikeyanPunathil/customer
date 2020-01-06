package com.diviso.graeshoppe.customer.client.SMS;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "SMSResource", url= "${smsgateway.in-url}")
public interface SMSResourceApiClientIN extends SMSResourceApiIN{

}
