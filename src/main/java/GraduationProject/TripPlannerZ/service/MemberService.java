package GraduationProject.TripPlannerZ.service;

import GraduationProject.TripPlannerZ.domain.Member;
import GraduationProject.TripPlannerZ.dto.MemberTrip;
import GraduationProject.TripPlannerZ.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검색
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    // 여행 일정 검색
    public Page<MemberTrip> findTrip(String email, Pageable pageable) {
        return memberRepository.tripList(email, pageable);
    }

    // 회원 탈퇴
    public void exit(Member member) {
        member.exit();
        memberRepository.delete(member);
    }
}
