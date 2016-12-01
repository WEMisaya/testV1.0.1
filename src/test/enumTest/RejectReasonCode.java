package test.enumTest;

import java.util.HashMap;

public enum RejectReasonCode {
	// 0001：删除终端不存在
	// 0002：文件、记录格式错误。
	// 0003：必填要素为空
	// 0004：某要素没按文件格式要求填写
	// 0005：终端对应商户编码不存在
	// 0006：收单机构代码不足10位
	// 0007：其他原因。

	// 0001：删除商户不存在
	// 0002：文件、记录格式错误
	// 0003：必填要素为空
	// 0004：某要素没按文件格式要求填写
	// 0005：商户非本机构商户
	// 0006：收单机构代码不足10位
	// 0007：MCC为公示类商户MCC
	// 0008：其他原因

	DelMerNoexist_M("0001", "删除终端不存在", 1), FormatError_M("0002", "文件、记录格式错误", 1), ElementsEmpty_M("0003", "必填要素为空",
			1), ElementsError_M("0004", "某要素没按文件格式要求填写", 1), MerError_M("0005", "商户非本机构商户", 1), AgentNoError_M("0006",
					"收单机构代码不足10位", 1), MCCError_M("0007", "MCC为公示类商户MCC", 1), Other_M("0008", "其他原因", 1),

	DelTermNoexist("0001", "删除终端不存在", 2), FormatError("0002", "文件、记录格式错误", 2), ElementsEmpty("0003", "必填要素为空",
			2), ElementsError("0004", "某要素没按文件格式要求填写", 2), MerNoexist("0005", "终端对应商户编码不存在",
					2), AgentNoError("0006", "收单机构代码不足10位", 2), Other("0007", "其他原因", 2);

	private String code;
	private String reason;
	private static HashMap<String, String> ReasonMap = new HashMap<String, String>() {
		{
			put("0001", "删除终端不存在");
			put("0002", "文件、记录格式错误");
			put("0003", "必填要素为空");
			put("0004", "某要素没按文件格式要求填写");
			put("0005", "商户非本机构商户");
			put("0006", "收单机构代码不足10位");
			put("0007", "MCC为公示类商户MCC");
			put("0008", "其他原因");
		}
	};

	private RejectReasonCode(String code, String reason, int i) {
		this.code = code;
		this.reason = reason;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
