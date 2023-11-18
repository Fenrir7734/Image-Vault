export interface ConfirmationConfig {
  header: string;
  message: string;
  closable?: boolean;
  accept?: string;
  reject?: string;
  onAccept?: () => void;
  onReject?: () => void;
}
