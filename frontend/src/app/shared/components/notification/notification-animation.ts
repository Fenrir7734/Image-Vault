import { animate, animateChild, query, state, style, transition, trigger } from '@angular/animations';

const triggerChildAnimation = trigger('triggerChildAnimation', [
  transition(':enter, :leave', [animate('0s'), query('*', [animateChild()])]),
]);

const verticalCollapseAnimation = trigger('verticalCollapse', [
  state('*', style({ height: '*' })),
  state('void', style({ height: '0' })),
  transition('* => void', animate('.3s .3s ease')),
]);

export const notificationAnimation = [
  trigger('slideIn', [
    state(
      '*',
      style({
        transform: 'translateY(0)',
        opacity: 1,
      })
    ),
    state(
      'void',
      style({
        transform: 'translateY(20px)',
        opacity: 0,
      })
    ),
    transition('void => *', animate('.3s')),
  ]),
  trigger('slideOut', [
    state(
      '*',
      style({
        transform: 'translateX(0)  scale(1)',
        opacity: 1,
      })
    ),
    state(
      'void',
      style({
        transform: 'translateX(100%)',
        opacity: 0,
      })
    ),
    transition('* => void', animate('.2s')),
  ]),
  verticalCollapseAnimation,
  triggerChildAnimation,
];
