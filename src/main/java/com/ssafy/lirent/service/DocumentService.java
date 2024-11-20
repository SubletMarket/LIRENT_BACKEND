package com.ssafy.lirent.service;

import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

@Service
public class DocumentService {

    private static final String TEMPLATE_PATH = "src/main/resources/contract/contractForm.docx";
    private static final String OUTPUT_DIR = "src/main/resources/contract/ContractResults";

    public String generateContract(Map<String, String> replacements) throws Exception {
        File templateFile = new File(TEMPLATE_PATH);
        File outputDir = new File(OUTPUT_DIR);

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        String tenantName = replacements.get("bN");
        if (tenantName == null || tenantName.isEmpty()) {
            throw new IllegalArgumentException("전차인 이름($bN)이 입력되지 않았습니다.");
        }

        String outputFileName = tenantName + "_Contract.docx";
        File outputFile = new File(outputDir, outputFileName);

        // 템플릿 파일 로드
        try (FileInputStream templateStream = new FileInputStream(templateFile);
             XWPFDocument docx = new XWPFDocument(templateStream)) {

            // 문단 내 텍스트 교체
            for (XWPFParagraph paragraph : docx.getParagraphs()) {
                replaceParagraphText(paragraph, replacements);
            }

            // 표 내 텍스트 교체
            for (XWPFTable table : docx.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            replaceParagraphText(paragraph, replacements);
                        }
                    }
                }
            }

            // 결과물 저장
            try (FileOutputStream outStream = new FileOutputStream(outputFile)) {
                docx.write(outStream);
            }
        }

        return outputFile.getAbsolutePath();
    }

    /**
     * 문단 텍스트 교체. 텍스트가 여러 XWPFRun으로 나뉘어 있을 경우도 처리.
     */
    private void replaceParagraphText(XWPFParagraph paragraph, Map<String, String> replacements) {
        // 문단 내 텍스트를 모두 합침
        StringBuilder fullText = new StringBuilder();
        for (XWPFRun run : paragraph.getRuns()) {
            fullText.append(run.getText(0));
        }

        // 텍스트 교체
        String replacedText = fullText.toString();
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            replacedText = replacedText.replace("$" + entry.getKey(), entry.getValue());
        }

        // 기존 Runs 삭제
        for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
            paragraph.removeRun(i);
        }

        // 새로운 Run 생성
        XWPFRun newRun = paragraph.createRun();
        newRun.setText(replacedText);
    }
}

/*
{
    "ad": "서울특별시 강남구",
    "ma": "10,000,000",
    "mm": "500,000",
    "Y": "24",
    "M": "11",
    "D": "20",
    "eY": "23",
    "eM": "01",
    "eD": "01",
    "cY": "23",
    "cM": "12",
    "cD": "31",
    "aNum": "123456-1234567",
    "Pn": "010-1234-5678",
    "aN": "동길홍",
    "bNum": "233456-1234567",
    "bPN": "010-5678-1234",
    "bN": "길홍동"
}
*/