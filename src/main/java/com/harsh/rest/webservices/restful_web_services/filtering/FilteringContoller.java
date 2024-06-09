package com.harsh.rest.webservices.restful_web_services.filtering;

import java.util.*;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringContoller 
{
    //static filtering
    @GetMapping("/filtering")
    public SomeBean filtering()
    {
        return new SomeBean("value1", "value2", "value3");
    }

    //static filtering
    @GetMapping("/filtering-list")
    public List<SomeBean> filteringList()
    {
        return Arrays.asList(new SomeBean("value1", "value2", "value3"), 
                            new SomeBean("value4", "value5", "value6"));
    }

    //dynamic filtering
    @GetMapping("/Dynamic-filtering")  //field2
    public MappingJacksonValue DyanamicFiltering()
    {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        mappingJacksonValue.setFilters(filters);
        
        return mappingJacksonValue;
    }

    //dynamic filtering
    @GetMapping("/Dynamic-filtering-list") //field2, field3
	public MappingJacksonValue DyanamicFilteringList() {
		List<SomeBean> list = Arrays.asList(new SomeBean("value1","value2", "value3"),
				new SomeBean("value4","value5", "value6"));
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		
		SimpleBeanPropertyFilter filter = 
				SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		
		FilterProvider filters = 
				new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		
		mappingJacksonValue.setFilters(filters );
		
		
		return mappingJacksonValue;
	}

}
