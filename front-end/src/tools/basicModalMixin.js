export const basicModalMixin = {
	data() {
		return {
			openModal: false,
    modalContent: null,
		}
	},
	methods: {
		// 모달 닫기
    modalClose() {
      this.openModal = false
      this.modalContent = null
    }
	}
}