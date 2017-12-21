/*******************************************************************************
 * Copyright 2017 Johns Hopkins University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This software was produced as part of the RMap Project (http://rmap-project.info),
 * The RMap Project was funded by the Alfred P. Sloan Foundation and is a 
 * collaboration between Data Conservancy, Portico, and IEEE.
 *******************************************************************************/
package info.rmapproject.webapp.controllers;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.net.URLEncoder;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.common.net.MediaType;

import info.rmapproject.testdata.service.TestFile;
import info.rmapproject.webapp.WebDataRetrievalTestAbstract;

public class DiSCODisplayControllerTest extends WebDataRetrievalTestAbstract {
	
    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    
	/**
     * Try DiSCO redirect to API when RDF type requested.
     * @throws Exception
     */
    @Test
    public void testGetDiSCO() throws Exception {
		ORMapDiSCO disco = getRMapDiSCOObj(TestFile.DISCOB_V1_XML);
		String discoUri = disco.getId().toString();
        assertNotNull(discoUri);
		rmapService.createDiSCO(disco, reqEventDetails);
		discoUri = URLEncoder.encode(discoUri, "UTF-8");
        mockMvc.perform(get("/discos/" + discoUri).accept("text/html"))
        	.andExpect(view().name("discos"))
        	.andExpect(status().isOk());
    }
    
    
    
	/**
     * Try DiSCO redirect to API when RDF type requested.
     * @throws Exception
     */
    @Test
    public void testDiSCOContentNegotiation() throws Exception {
		ORMapDiSCO disco = getRMapDiSCOObj(TestFile.DISCOB_V1_XML);
		String discoUri = disco.getId().toString();
        assertNotNull(discoUri);
		rmapService.createDiSCO(disco, reqEventDetails);
		discoUri = URLEncoder.encode(discoUri, "UTF-8");
        mockMvc.perform(get("/discos/" + discoUri).accept(MediaType.RDF_XML_UTF_8.toString()))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("https://fake.rmap-hub.org/fake/discos/" + discoUri));

        mockMvc.perform(get("/discos/" + discoUri).accept("text/turtle"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("https://fake.rmap-hub.org/fake/discos/" + discoUri));
    }
	
}
