package indravex.FinTrack.Pro.DashboardManagement.controller;

import indravex.FinTrack.Pro.DashboardManagement.dto.DashboardResponse;
import indravex.FinTrack.Pro.DashboardManagement.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponse getDashboard(
            @RequestParam(required = false) Long companyId) {

        return dashboardService.getDashboard(companyId);
    }
}