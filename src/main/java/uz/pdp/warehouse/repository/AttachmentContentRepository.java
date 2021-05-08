package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.AttachmentContent;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Long> {
    AttachmentContent findByAttachmentId(Long attachment_id);
    void deleteByAttachment_Id(Long attachment_id);
}
