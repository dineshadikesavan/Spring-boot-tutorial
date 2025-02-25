package com.dailycodebuffer.springboot.tutorial.controller;

import com.dailycodebuffer.springboot.tutorial.entity.Department;
import com.dailycodebuffer.springboot.tutorial.exception.DepartmentNotFoundException;
import com.dailycodebuffer.springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder().departmentName("IT").departmentAddress("Chennai").departmentCode("IT-02").departmentId(1L).build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDept = Department.builder().departmentName("IT").departmentAddress("Chennai").departmentCode("IT-02").build();
        Mockito.when(departmentService.saveDepartment(inputDept)).thenReturn(department);
        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\t\"departmentName\": \"ORG\",\n" +
                        "\t\"departmentAddress\": \"Mumbai\",\n" +
                        "\t\"departmentCode\": \"ORG-05\"\n" +
                        "}")
        ).andExpect(status().isOk());
    }

    @Test
    void getDepartmentById() throws Exception {
        Long id = 1L;
        Mockito.when(departmentService.getDepartmentById(id)).thenReturn(department);
        mockMvc.perform(get("/departments/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath(("$.departmentName")).value(department.getDepartmentName()));
    }

    @Test
    void getDepartmentByName() throws Exception {
        String name = "ORG";
        Mockito.when(departmentService.getDepartmentByName(name)).thenReturn(department);
        mockMvc.perform(get("/departments/name/" + name)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.departmentName")).value(department.getDepartmentName()));
    }
}