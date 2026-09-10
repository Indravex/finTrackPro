package indravex.FinTrack.Pro.DashboardManagement.service;

import indravex.FinTrack.Pro.DashboardManagement.dto.DashboardResponse;

public interface DashboardService {

    DashboardResponse getDashboard(Long companyId);
}
